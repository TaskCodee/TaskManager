import { Button, VStack, Box } from '@chakra-ui/react';
import { useEffect, useState } from 'react';
import { CardInfo, ListInfo } from './App';
import BoardCard from './BoardCard';

const BoardList = ({
  listInfo,
  createCard,
}: {
  listInfo: ListInfo;
  createCard: (listId: number, cardInfo?: CardInfo) => void;
}) => {
  const [cards, setCards] = useState<CardInfo[]>(listInfo.cards);

  useEffect(() => {
    setCards(listInfo.cards);
  }, [listInfo.cards]);

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
        <Button mt={'0.5em'} onClick={() => createCard(listInfo.id)}>
          +
        </Button>
      </Box>
    </>
  );
};

export default BoardList;
