import { Button, HStack } from '@chakra-ui/react';
import BoardList from './BoardList';
import { useEffect, useState } from 'react';
import { randomId } from './util';

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
  const [lists, setLists] = useState<ListInfo[]>([]);

  useEffect(() => {
    let ignore = false;

    const fetchPosts = async () => {
      const lists = (
        await (await fetch('/api/newBoards/1')).json()
      ).cardListCardDTOlist.map((l: any) => ({
        id: l.id,
        title: l.title,
        cards: l.cardDTOList,
      }));
      console.log(lists);

      if (!ignore) {
        setLists(lists);
      }
    };

    fetchPosts();

    return () => {
      ignore = true;
    };
  }, []);

  const spawnList = () => {
    setLists((prev) => [
      ...prev,
      { id: randomId(), title: 'New List', cards: [] },
    ]);
  };

  return (
    <>
      <HStack align={'flex-start'} gap={'1em'} p={'1em'}>
        <>
          {lists.length > 0 &&
            lists.map((list) => <BoardList listInfo={list} key={list.id} />)}
          <Button onClick={() => spawnList()}>+</Button>
        </>
      </HStack>
    </>
  );
}

export default App;
