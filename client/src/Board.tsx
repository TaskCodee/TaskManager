import { BoardData } from './lib/api';
import { SmallAddIcon } from '@chakra-ui/icons';
import { HStack, Button, Skeleton } from '@chakra-ui/react';
import BoardList from './BoardList';
import { useEffect, useState } from 'react';

const Board = ({ boardInfo }: { boardInfo: BoardData }) => {
  const [board, setBoard] = useState<BoardData>();

  useEffect(() => {
    (async () => {
      const res = await fetch(`/api/boards/${boardInfo.id}`);
      const data = await res.json();
      setBoard(data);
    })();
  }, [boardInfo.id]);

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
