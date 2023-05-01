import { Button, HStack, Input } from '@chakra-ui/react';
import BoardList from './BoardList';
import { ChangeEvent, useCallback, useEffect, useState } from 'react';

export type CardInfo = {
  id: number;
  title: string;
  description: string;
};

export type ListInfo = {
  id: number;
  title: string;
  cards: CardInfo[];
};

function App() {
  const [boardId, setBoardId] = useState(1);
  const [lists, setLists] = useState<ListInfo[]>([]);

  const fetchBoard = useCallback(async () => {
    const res = await fetch(`/api/boards/${boardId}`);
    if (!res.ok) return;

    const { lists } = await res.json();
    console.log(lists);

    setLists(lists);
  }, [boardId]);

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

    fetchBoard();
  };

  const createCard = async (listId: number, cardInfo?: CardInfo) => {
    const card = cardInfo || { title: 'test card' };
    const data = { cardListId: listId, ...card };

    const res = await fetch('/api/cards', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data),
    });

    if (res.ok) fetchBoard();
  };

  useEffect(() => {
    fetchBoard();
  }, [fetchBoard]);

  return (
    <>
      <Input
        type={'number'}
        value={boardId}
        onChange={(e: ChangeEvent<HTMLInputElement>) =>
          setBoardId(Number(e.target.value))
        }
      />
      <HStack align={'flex-start'} gap={'1em'} p={'1em'}>
        <>
          {lists.length > 0 &&
            lists.map((list) => (
              <BoardList
                listInfo={list}
                createCard={createCard}
                key={list.id}
              />
            ))}
          <Button onClick={() => createList()}>+</Button>
        </>
      </HStack>
    </>
  );
}

export default App;
