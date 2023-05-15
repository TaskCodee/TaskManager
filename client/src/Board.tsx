import { BoardInfo, addList, fetcher } from './lib/api';
import { SmallAddIcon } from '@chakra-ui/icons';
import { HStack, Button, Skeleton } from '@chakra-ui/react';
import BoardList from './BoardList';
import useSWR from 'swr';

const Board = ({ board: boardInfo }: { board: BoardInfo }) => {
  const { data: board, mutate: mutateBoard } = useSWR<BoardInfo | undefined>(
    `/api/boards/${boardInfo.id}`,
    fetcher
  );

  return (
    <>
      <HStack align={'flex-start'} gap={'1em'}>
        {board ? (
          <>
            {board.lists?.map((list) => (
              <BoardList listInfo={list} key={list.id} />
            ))}
            <Button
              onClick={() => {
                const title = 'Test list';

                mutateBoard((curBoard) => addList(curBoard, { title }), {
                  optimisticData: (curBoard) => {
                    if (!curBoard) return;

                    return {
                      ...curBoard,
                      lists: [...curBoard.lists, { id: 0, title, cards: [] }],
                    };
                  },
                  rollbackOnError: true,
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
      </HStack>
    </>
  );
};

export default Board;
