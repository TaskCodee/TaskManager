import { BoardData, BoardInfo } from './lib/api';
import { SmallAddIcon } from '@chakra-ui/icons';
import { HStack, Button, Skeleton } from '@chakra-ui/react';
import BoardList from './BoardList';
import { useQuery } from '@tanstack/react-query';

const Board = ({ boardInfo }: { boardInfo: BoardInfo }) => {
  const fetchBoard = async () => {
    const res = await fetch(`/api/boards/${boardInfo.id}`);
    const data = await res.json();
    return data;
  };

  const { data: board } = useQuery<BoardData>({
    queryKey: ['board'],
    queryFn: fetchBoard,
  });

  return (
    <>
      <HStack align={'flex-start'} gap={'1em'}>
        {board ? (
          <>
            {board.lists.map((list) => (
              <BoardList list={list} key={list.id} />
            ))}
            <Button onClick={() => console.log('Create list')}>
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
