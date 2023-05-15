import { SmallAddIcon } from '@chakra-ui/icons';
import { HStack, Button, Skeleton, Box } from '@chakra-ui/react';
import BoardList from './BoardList';
import { BoardData, ListInfo, createList, deleteList, randId } from './lib/api';
import { useMutation, useQueryClient } from '@tanstack/react-query';
import {
  DragDropContext,
  Draggable,
  Droppable,
  OnDragEndResponder,
} from 'react-beautiful-dnd';
import { useCallback } from 'react';

const Board = ({ board }: { board: BoardData }) => {
  const queryClient = useQueryClient();

  const listCreateMutation = useMutation({
    mutationFn: (newList: ListInfo) => createList(board.id, newList),
    onMutate: (newList) => {
      const prevBoard = queryClient.getQueryData(['board', board.id]);
      queryClient.setQueryData(
        ['board', board.id],
        (old: BoardData | undefined) =>
          old
            ? { ...old, lists: [...old.lists, { ...newList, cards: [] }] }
            : old
      );
      return { prevBoard };
    },
    onSuccess: () => queryClient.invalidateQueries(['board']),
  });

  const listDeleteMutation = useMutation({
    mutationFn: (listId: number | undefined) => deleteList(listId),
    onSuccess: () => queryClient.invalidateQueries(['board']),
  });

  const onDragEnd: OnDragEndResponder = useCallback((result) => {
    console.log('Drop end: ', result);
  }, []);

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
