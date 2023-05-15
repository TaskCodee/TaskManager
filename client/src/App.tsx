import { Button, HStack, Skeleton } from '@chakra-ui/react';
import BoardList from './BoardList';
import { useEffect, useState } from 'react';
import BoardSelector from './BoardSelector';
import { SmallAddIcon } from '@chakra-ui/icons';
import {
  useBoard,
  useBoards,
  useCreateBoard,
  useCreateCard,
  useCreateList,
  useDeleteList,
} from './lib/api';

function App() {
  const [boardIndex, setBoardIndex] = useState<number>(0);
  const { triggerDeleteList } = useDeleteList();
  const { boards, boardsMutate } = useBoards();
  const { board, boardMutate } = useBoard(
    boards ? boards[boardIndex].id : null
  );

  const { triggerCreateBoard } = useCreateBoard();
  const { triggerCreateList } = useCreateList();
  const { triggerCreateCard } = useCreateCard();

  useEffect(() => {
    if (boards) setBoardIndex(boards?.length - 1);
  }, [boards]);

  const createBoard = async (
    title = Math.random().toString(36).slice(2, 5)
  ) => {
    triggerCreateBoard({ userId: 1, title });

    await boardsMutate();
    await boardMutate();
  };

  const createList = async (boardId: number, title = 'Test list') => {
    await triggerCreateList({ boardId, title });

    await boardMutate();
  };

  const deleteList = async (id: number) => {
    await triggerDeleteList({ id });

    await boardMutate();
  };

  const createCard = async (
    listId: number,
    title = 'Test card',
    description = 'Test card'
  ) => {
    await triggerCreateCard({ cardListId: listId, title, description });

    await boardMutate();
  };

  return (
    <>
      <HStack p={'1em'}>
        <Button onClick={() => createBoard()}>
          <SmallAddIcon />
        </Button>
        <BoardSelector
          boardIndex={boardIndex}
          boards={boards}
          setBoardIndex={setBoardIndex}
        />
      </HStack>
      {board ? (
        <>
          <HStack align={'flex-start'} gap={'1em'} p={'1em'}>
            {board.lists?.map((list) => (
              <BoardList
                createCard={createCard}
                deleteList={deleteList}
                listInfo={list}
                key={list.id}
              />
            ))}
            <Button onClick={() => createList(board.id)}>
              <SmallAddIcon />
            </Button>
          </HStack>
        </>
      ) : (
        <HStack>
          <Skeleton borderRadius={'md'} m={'1em'} w={'16em'} h={'20em'} />
          <Skeleton borderRadius={'md'} m={'1em'} w={'16em'} h={'20em'} />
        </HStack>
      )}
    </>
  );
}

export default App;
