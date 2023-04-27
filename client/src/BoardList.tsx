import { Button, VStack, Box } from '@chakra-ui/react';
import { useEffect, useState } from 'react';
import { CardInfo, ListInfo } from './App';
import BoardCard from './BoardCard';

const BoardList = ({ listInfo }: { listInfo: ListInfo }) => {
  const [cards, setCards] = useState<CardInfo[]>(listInfo.cards);

  useEffect(() => {
    setCards(listInfo.cards);
  }, [listInfo.cards]);

  const spawnCard = (cardInfo?: CardInfo) => {
    const card = cardInfo || {
      id: Math.floor(Math.random() * 1000000),
      title: 'Test',
      description: 'Test description',
    };

    setCards((prev) => [...prev, card]);
  };

  return (
    <>
      <Box shadow={'md'} borderRadius={'15'} p={'1.5em'}>
        <Box fontWeight={'semibold'} textAlign={'start'} m={'1em'}>
          {listInfo.title}
        </Box>
        <VStack w={'16em'}>
          {cards.map((card) => (
            <BoardCard cardInfo={card} key={card.id} />
          ))}
        </VStack>
        <Button mt={'0.5em'} onClick={() => spawnCard()}>
          +
        </Button>
      </Box>
    </>
  );
};

export default BoardList;
