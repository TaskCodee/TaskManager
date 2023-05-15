import { Box, Button, HStack, Skeleton } from '@chakra-ui/react';
import { useState } from 'react';
import BoardSelector from './BoardSelector';
import { SmallAddIcon } from '@chakra-ui/icons';
import Board from './Board';
import { getBoard, getBoards as getBoards } from './lib/api';
import { useQuery } from '@tanstack/react-query';

function App() {
  const [boardIndex, setBoardIndex] = useState<number>(0);

  const { data: boards } = useQuery({
    queryKey: ['boards'],
    queryFn: getBoards,
  });

  const boardId = boards?.[0].id;

  const { data: board } = useQuery({
    queryKey: ['board'] as const,
    queryFn: () => getBoard(boardId),
    enabled: boardId != null,
  });

  return (
    <Box p={'1em'}>
      <>
        <Skeleton isLoaded={boards !== undefined}>
          {boards && (
            <>
              <HStack>
                <Button>
                  <SmallAddIcon />
                </Button>
                <BoardSelector
                  boardIndex={boardIndex}
                  boards={boards}
                  setBoardIndex={setBoardIndex}
                />
              </HStack>
              <Box mt={'1em'}>{board && <Board board={board} />}</Box>
            </>
          )}
        </Skeleton>
      </>
    </Box>
  );
}

export default App;
