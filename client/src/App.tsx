import { Button, HStack, Skeleton } from '@chakra-ui/react';
import BoardList from './BoardList';
import { useCallback, useEffect, useState } from 'react';
import BoardSelector from './BoardSelector';
import { SmallAddIcon } from '@chakra-ui/icons';

export type BoardInfo = {
  id: number;
  title: string;
};

export type ListInfo = {
  id: number;
  title: string;
  cards: CardInfo[];
};

export type CardInfo = {
  id: number;
  title: string;
  description: string;
};

function App() {
  const [boards, setBoards] = useState<BoardInfo[]>([]);
  const [boardId, setBoardId] = useState<number>();
  const [lists, setLists] = useState<ListInfo[]>();

  const selectBoard = (id: number) => {
    setLists(undefined);
    setBoardId(id);
  };

  const fetchAllBoards = useCallback(async () => {
    const res = await fetch('/api/boards');
    if (!res.ok) return;
    const data: BoardInfo[] = await res.json();
    setBoards(data);

    const last = data.at(data.length - 1);
    if (last) selectBoard(last.id);
  }, []);

  const fetchBoard = async (id?: number) => {
    if (!id) {
      setLists(undefined);
      return;
    }

    const res = await fetch(`/api/boards/${id}`);
    if (res.ok) {
      const data = await res.json();
      setLists(data.lists);
    } else {
      setLists(undefined);
    }
  };

  const createBoard = async (
    title = Math.random().toString(36).slice(2, 5)
  ) => {
    const res = await fetch('/api/boards', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ userId: 1, title }),
    });

    if (!res.ok) return;

    fetchAllBoards();
  };

  const createList = async (listInfo?: ListInfo) => {
    const data = listInfo || {
      boardId,
      title: 'Test list',
    };

    const res = await fetch('/api/lists', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data),
    });
    if (!res.ok) return;

    fetchBoard(boardId);
  };

  const deleteList = async (id: number) => {
    const res = await fetch(`/api/lists/${id}`, {
      method: 'DELETE',
    });
    if (!res.ok) return;

    fetchBoard(boardId);
  };

  const createCard = async (listId: number, cardInfo?: CardInfo) => {
    const card = cardInfo || { title: 'test card' };
    const data = { cardListId: listId, ...card };

    const res = await fetch('/api/cards', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data),
    });

    if (!res.ok) return;

    fetchBoard(boardId);
  };

  useEffect(() => {
    fetchAllBoards();
  }, [fetchAllBoards]);

  useEffect(() => {
    fetchBoard(boardId);
  }, [boardId]);

  return (
    <>
      <HStack p={'1em'}>
        <Button onClick={() => createBoard()}>
          <SmallAddIcon />
        </Button>
        <BoardSelector
          boardId={boardId}
          boards={boards}
          selectBoard={selectBoard}
        />
      </HStack>
      {lists ? (
        <>
          <HStack align={'flex-start'} gap={'1em'} p={'1em'}>
            {lists.map((list) => (
              <BoardList
                listInfo={list}
                createCard={createCard}
                deleteList={deleteList}
                key={list.id}
              />
            ))}
            <Button onClick={() => createList()}>
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
