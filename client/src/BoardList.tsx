import {
  Button,
  VStack,
  Box,
  Menu,
  MenuButton,
  MenuItem,
  MenuList,
  Flex,
  Spacer,
} from '@chakra-ui/react';
import BoardCard from './BoardCard';
import { AddIcon, ChevronDownIcon } from '@chakra-ui/icons';
import {
  BoardData,
  CardInfo,
  ListData,
  createCard,
  moveCard,
  randId,
} from './lib/api';
import { useMutation, useQueryClient } from '@tanstack/react-query';
import {
  DragDropContext,
  Draggable,
  DraggableProvidedDragHandleProps,
  Droppable,
  OnDragEndResponder,
} from 'react-beautiful-dnd';
import { produce } from 'immer';

const BoardList = ({
  list,
  boardId,
  deleteList,
  dragHandleProps,
}: {
  list: ListData;
  boardId: number;
  deleteList: (listId: number) => void;
  dragHandleProps: DraggableProvidedDragHandleProps | null | undefined;
}) => {
  const queryClient = useQueryClient();

  const cardCreateMutation = useMutation({
    mutationFn: (newCard: CardInfo) => createCard(list.id, newCard),
    onMutate: (newCard) => {
      const prevBoard: BoardData | undefined = queryClient.getQueryData([
        'board',
        boardId,
      ]);
      queryClient.setQueryData(
        ['board', boardId],
        produce(prevBoard, (draft) => {
          if (!draft) return;
          const listIndex = draft.lists.findIndex((l) => l.id === list.id);
          draft.lists[listIndex].cards.push(newCard);
        })
      );
      return { prevBoard };
    },
    onSuccess: () => queryClient.invalidateQueries(['board']),
  });

  const cardMoveMutation = useMutation({
    mutationFn: ({
      cardId,
      destination,
    }: {
      cardId: number;
      destination: { listIndex: number; cardIndex: number };
    }) => moveCard(cardId, destination),
    onMutate: ({ cardId, destination }) => {
      const prevBoard: BoardData | undefined = queryClient.getQueryData([
        'board',
        boardId,
      ]);
      queryClient.setQueryData(
        ['board', boardId],
        produce(prevBoard, (draft) => {
          if (!draft) return;
          const listIndex = draft.lists.findIndex((l) => l.id === list.id);
          const cardIndex = draft.lists[listIndex].cards.findIndex(
            (c) => c.id === cardId
          );
          const card = draft.lists[listIndex].cards[cardIndex];
          draft.lists[listIndex].cards.splice(cardIndex, 1);
          draft.lists[destination.listIndex].cards.splice(
            destination.cardIndex,
            0,
            card
          );
        })
      );
      return { prevBoard };
    },
    onSuccess: () => queryClient.invalidateQueries(['board']),
  });

  const onDragEnd: OnDragEndResponder = (result) => {
    console.log('Card drag: ', result);
    console.log(`${result.source.index} -> ${result.destination?.index}`);
    const cardIndex = result.destination?.index;
    if (cardIndex == null) return;
    const board: BoardData | undefined = queryClient.getQueryData([
      'board',
      boardId,
    ]);
    if (!board) return;
    const destListIndex = board.lists.findIndex((l) => l.id === list.id);
    const destCardIndex = result.destination?.index;
    if (destCardIndex == null) return;

    const destination = { listIndex: destListIndex, cardIndex: destCardIndex };
    cardMoveMutation.mutate({
      cardId: Number(result.draggableId),
      destination,
    });
  };

  return (
    <DragDropContext onDragEnd={onDragEnd}>
      <Box shadow={'md'} borderRadius={'15'}>
        <Flex
          fontWeight={'semibold'}
          textAlign={'start'}
          mb={'0.5em'}
          align={'center'}
          {...dragHandleProps}
        >
          <Box>{list.title}</Box>
          <Spacer />
          <Menu>
            <MenuButton variant={'ghost'} as={Button}>
              <ChevronDownIcon />
            </MenuButton>
            <MenuList>
              <MenuItem
                onClick={() => {
                  console.log('Delete list');
                  deleteList(list.id);
                }}
              >
                Delete
              </MenuItem>
            </MenuList>
          </Menu>
        </Flex>

        <Droppable droppableId={'list'}>
          {(provided) => (
            <VStack
              w={'16em'}
              {...provided.droppableProps}
              ref={provided.innerRef}
            >
              {list.cards.map((card, index) => (
                <Draggable
                  index={index}
                  draggableId={card.id.toString()}
                  key={card.id}
                >
                  {(provided) => (
                    <Box ref={provided.innerRef} {...provided.draggableProps}>
                      <BoardCard
                        cardInfo={card}
                        dragHandleProps={provided.dragHandleProps}
                      />
                    </Box>
                  )}
                </Draggable>
              ))}
              {provided.placeholder}
            </VStack>
          )}
        </Droppable>
        <Button
          variant={'outline'}
          w={'100%'}
          mt={'0.5em'}
          onClick={() => {
            console.log('Create card');
            cardCreateMutation.mutate({
              id: randId(),
              title: 'Test card',
              description: '',
            });
          }}
        >
          <AddIcon />
        </Button>
      </Box>
    </DragDropContext>
  );
};

export default BoardList;
