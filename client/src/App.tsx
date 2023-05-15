import { Box, Button, HStack, Skeleton } from '@chakra-ui/react';
import { useState } from 'react';
import BoardSelector from './BoardSelector';
import { SmallAddIcon } from '@chakra-ui/icons';
import Board from './Board';
import {
  BoardInfo,
  createBoard,
  getBoard,
  getBoards as getBoards,
} from './lib/api';
import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query';

function App() {
  const userId = 1;
  const [boardIndex, setBoardIndex] = useState<number>(0);

  const queryClient = useQueryClient();

  const boardMutation = useMutation({
    mutationFn: (newBoard: BoardInfo) => createBoard(userId, newBoard),
    onSuccess: async () => {
      await queryClient.invalidateQueries(['boards']);
      queryClient.invalidateQueries(['board']);
    },
  });

  const { data: boards } = useQuery({
    queryKey: ['boards'],
    queryFn: getBoards,
  });

  const boardId = boards?.[boardIndex].id;

  const { data: board } = useQuery({
    queryKey: ['board', boardId] as const,
    queryFn: ({ queryKey }) => getBoard(queryKey[1]),
    enabled: boardId != null,
  });

  return (
    <Box p={'1em'}>
      <>
        <Skeleton isLoaded={boards !== undefined}>
          {boards && (
            <>
              <HStack>
                <Button
                  onClick={() => {
                    console.log('Created board');
                    boardMutation.mutate({
                      id: 0,
                      title: Math.random().toString(36).substr(2, 5),
                    });
                  }}
                >
                  <SmallAddIcon />
                </Button>
                <BoardSelector
                  boards={boards}
                  boardIndex={boardIndex}
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
