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
import { BoardData, CardInfo, ListData, createCard, randId } from './lib/api';
import { useMutation, useQueryClient } from '@tanstack/react-query';
import {
  DragDropContext,
  Draggable,
  DraggableProvidedDragHandleProps,
  Droppable,
  OnDragEndResponder,
} from 'react-beautiful-dnd';

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
      const prevBoard = queryClient.getQueryData(['board', boardId]);
      queryClient.setQueryData(
        ['board', boardId],
        (old: BoardData | undefined) => {
          if (!old) return old;
          const newBoard = { ...old };
          const listIndex = newBoard.lists.findIndex((l) => l.id === list.id);
          newBoard.lists[listIndex].cards = [
            ...newBoard.lists[listIndex].cards,
            newCard,
          ];
          console.log({ old, newBoard });

          return newBoard;
        }
      );
      return { prevBoard };
    },
    onSuccess: () => queryClient.invalidateQueries(['board']),
  });

  const onDragEnd: OnDragEndResponder = (result) => {
    console.log(result);
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
