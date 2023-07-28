import { SmallAddIcon } from '@chakra-ui/icons';
import { HStack, Button, Skeleton, Box } from '@chakra-ui/react';
import BoardList from './BoardList';
import {
  BoardData,
  ListInfo,
  createList,
  deleteList,
  moveList,
  randId,
} from './lib/api';
import { useMutation, useQueryClient } from '@tanstack/react-query';
import {
  DragDropContext,
  Draggable,
  Droppable,
  OnDragEndResponder,
} from 'react-beautiful-dnd';
import { useCallback } from 'react';
import { produce } from 'immer';

const Board = ({ board }: { board: BoardData }) => {
  const queryClient = useQueryClient();

  const listCreateMutation = useMutation({
    mutationFn: (newList: ListInfo) => createList(board.id, newList),
    onMutate: (newList) => {
      const prevBoard: BoardData | undefined = queryClient.getQueryData([
        'board',
        board.id,
      ]);
      queryClient.setQueryData(
        ['board', board.id],
        produce(prevBoard, (draft) => {
          if (!draft) return;
          draft.lists.push({ ...newList, cards: [] });
        })
      );
      return { prevBoard };
    },
    onError: (_err, _newList, context) => {
      queryClient.setQueriesData(['board', board.id], context?.prevBoard);
    },
    onSuccess: () => queryClient.invalidateQueries(['board']),
  });

  const listDeleteMutation = useMutation({
    mutationFn: (listId: number | undefined) => deleteList(listId),
    onMutate: (listId) => {
      const prevBoard: BoardData | undefined = queryClient.getQueryData([
        'board',
        board.id,
      ]);
      queryClient.setQueryData(
        ['board', board.id],
        produce(prevBoard, (draft) => {
          if (!draft) return;
          const index = draft.lists.findIndex((l) => {
            l.id === listId;
          });
          if (index !== -1) draft.lists.splice(index, 1);
        })
      );
      return { prevBoard };
    },
    onSuccess: () => queryClient.invalidateQueries(['board']),
  });

  const listMoveMutation = useMutation({
    mutationFn: ({
      listId,
      destination,
    }: {
      listId: number;
      destination: { listIndex: number };
    }) => moveList(listId, destination),
    onMutate: ({ listId, destination }) => {
      const prevBoard: BoardData | undefined = queryClient.getQueryData([
        'board',
        board.id,
      ]);
      queryClient.setQueryData(
        ['board', board.id],
        produce(prevBoard, (draft) => {
          if (!draft) return;
          const listIndex = draft.lists.findIndex((l) => l.id === listId);
          const list = draft.lists[listIndex];
          draft.lists.splice(listIndex, 1);
          draft.lists.splice(destination.listIndex, 0, list);
        })
      );
      return { prevBoard };
    },
    onSuccess: () => queryClient.invalidateQueries(['board']),
  });

  const onDragEnd: OnDragEndResponder = useCallback(
    (result) => {
      console.log('List drag: ', result);
      console.log(`${result.source.index} -> ${result.destination?.index}`);
      const listId = Number(result.draggableId);
      console.log({ listId });

      const destListIndex = result.destination?.index;
      if (destListIndex == null) return;

      const destination = { listIndex: destListIndex };
      console.log({ listId, destination });

      listMoveMutation.mutate({ listId, destination });
    },
    [listMoveMutation]
  );

  return (
    <DragDropContext onDragEnd={onDragEnd}>
      <Droppable droppableId={'board'} direction={'horizontal'}>
        {(provided) => (
          <HStack ref={provided.innerRef} align={'flex-start'} gap={'1em'}>
            {board ? (
              <>
                {board.lists.map((list, index) => (
                  <Draggable
                    draggableId={list.id.toString()}
                    key={list.id}
                    index={index}
                  >
                    {(provided) => (
                      <Box ref={provided.innerRef} {...provided.draggableProps}>
                        <BoardList
                          list={list}
                          boardId={board.id}
                          key={list.id}
                          deleteList={(listId: number) =>
                            listDeleteMutation.mutate(listId)
                          }
                          dragHandleProps={provided.dragHandleProps}
                        />
                      </Box>
                    )}
                  </Draggable>
                ))}
                <Button
                  onClick={() => {
                    console.log('Create list');
                    listCreateMutation.mutate({
                      id: randId(),
                      title: 'Test list',
                    });
                  }}
                >
                  <SmallAddIcon />
                </Button>
              </>
            ) : (
              <>
                <Skeleton borderRadius={'md'} w={'16em'} h={'20em'} />
                <Skeleton borderRadius={'md'} w={'16em'} h={'20em'} />
              </>
            )}
            {provided.placeholder}
          </HStack>
        )}
      </Droppable>
    </DragDropContext>
  );
};

export default Board;
