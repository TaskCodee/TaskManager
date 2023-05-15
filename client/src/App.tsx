import { Box, Button, HStack, Skeleton } from '@chakra-ui/react';
import { useEffect, useState } from 'react';
import BoardSelector from './BoardSelector';
import { SmallAddIcon } from '@chakra-ui/icons';
import Board from './Board';
import { BoardData } from './lib/api';

function App() {
  const [boardIndex, setBoardIndex] = useState<number>(0);
  const [boards, setBoards] = useState<BoardData[]>([]);

  useEffect(() => {
    (async () => {
      const res = await fetch('/api/boards');
      const data = await res.json();

      setBoards(data);
    })();
  }, []);

  return (
    <Box p={'1em'}>
      <>
        <Skeleton isLoaded={boards !== undefined}>
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
          <Box mt={'1em'}>
            {boards[boardIndex] && <Board boardInfo={boards[boardIndex]} />}
          </Box>
        </Skeleton>
      </>
    </Box>
  );
}

export default App;
