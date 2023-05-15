import { SmallAddIcon } from '@chakra-ui/icons';
import { HStack, Button, Skeleton } from '@chakra-ui/react';
import BoardList from './BoardList';
import { BoardData, ListInfo, createList } from './lib/api';
import { useMutation, useQueryClient } from '@tanstack/react-query';

const Board = ({ board }: { board: BoardData }) => {
  const queryClient = useQueryClient();
  const listMutation = useMutation({
    mutationFn: (newList: ListInfo) => createList(board.id, newList),
    onSuccess: () => queryClient.invalidateQueries(['board']),
  });

  return (
    <>
      <HStack align={'flex-start'} gap={'1em'}>
        {board ? (
          <>
            {board.lists.map((list) => (
              <BoardList list={list} key={list.id} />
            ))}
            <Button
              onClick={() => {
                console.log('Create list');
                listMutation.mutate({ id: 0, title: 'Test card' });
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
