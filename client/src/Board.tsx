import { SmallAddIcon } from '@chakra-ui/icons';
import { HStack, Button, Skeleton } from '@chakra-ui/react';
import BoardList from './BoardList';
import { BoardData, ListInfo, createList, deleteList } from './lib/api';
import { useMutation, useQueryClient } from '@tanstack/react-query';

const Board = ({ board }: { board: BoardData }) => {
  const queryClient = useQueryClient();

  const listCreateMutation = useMutation({
    mutationFn: (newList: ListInfo) => createList(board.id, newList),
    onSuccess: () => queryClient.invalidateQueries(['board']),
  });

  const listDeleteMutation = useMutation({
    mutationFn: (listId: number | undefined) => deleteList(listId),
    onSuccess: () => queryClient.invalidateQueries(['board']),
  });

  return (
    <>
      <HStack align={'flex-start'} gap={'1em'}>
        {board ? (
          <>
            {board.lists.map((list) => (
              <BoardList
                list={list}
                key={list.id}
                deleteList={(listId: number) =>
                  listDeleteMutation.mutate(listId)
                }
              />
            ))}
            <Button
              onClick={() => {
                console.log('Create list');
                listCreateMutation.mutate({ id: 0, title: 'Test card' });
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
      </HStack>
    </>
  );
};

export default Board;
