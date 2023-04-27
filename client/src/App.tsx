import { Button, HStack } from '@chakra-ui/react';
import BoardList from './BoardList';
import { useEffect, useState } from 'react';

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
  const boardId = 1;
  const [lists, setLists] = useState<ListInfo[]>([]);

  const fetchBoard = async () => {
    const lists = (
      await (await fetch(`/api/newBoards/${boardId}`)).json()
    ).cardListCardDTOlist.map((l: any) => ({
      id: l.id,
      title: l.title,
      cards: l.cardDTOList,
    }));
    console.log(lists);

    setLists(lists);
  };

  const createList = async (listInfo?: ListInfo) => {
    const data = listInfo || {
      boardId,
      title: 'Test list',
    };

    const res = await fetch('/api/cardLists', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data),
    });

    if (res.ok) fetchBoard();
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
  }, []);

  return (
    <>
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
