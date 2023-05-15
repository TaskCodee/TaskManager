import { Box, Button, HStack, Skeleton } from '@chakra-ui/react';
import { useState } from 'react';
import BoardSelector from './BoardSelector';
import { SmallAddIcon } from '@chakra-ui/icons';
import Board from './Board';
import useSWR from 'swr';
import { BoardInfo, fetcher } from './lib/api';

function App() {
  const [boardIndex, setBoardIndex] = useState<number>(0);
  const { data: boards } = useSWR<BoardInfo[]>('/api/boards', fetcher);

  return (
    <Box p={'1em'}>
      <>
        <Skeleton isLoaded={!!boards}>
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
          <Box mt={'1em'}>{boards && <Board board={boards[0]} />}</Box>
        </Skeleton>
      </>
    </Box>
  );
}

export default App;
