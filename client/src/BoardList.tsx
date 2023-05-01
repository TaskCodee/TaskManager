import {
  Button,
  VStack,
  Box,
  Menu,
  MenuButton,
  MenuItem,
  MenuList,
  Flex,
  Spacer,
} from '@chakra-ui/react';
import { useEffect, useState } from 'react';
import { CardInfo, ListInfo } from './App';
import BoardCard from './BoardCard';

const BoardList = ({
  listInfo,
  createCard,
  deleteList,
}: {
  listInfo: ListInfo;
  createCard: (listId: number, cardInfo?: CardInfo) => void;
  deleteList: (id: number) => void;
}) => {
  const [cards, setCards] = useState<CardInfo[]>(listInfo.cards);

  useEffect(() => {
    setCards(listInfo.cards);
  }, [listInfo.cards]);

  return (
    <>
      <Box shadow={'md'} borderRadius={'15'} p={'1.5em'}>
        <Flex
          fontWeight={'semibold'}
          textAlign={'start'}
          mb={'0.5em'}
          align={'center'}
        >
          <Box>{listInfo.title}</Box>
          <Spacer />
          <Menu>
            <MenuButton as={Button}>...</MenuButton>
            <MenuList>
              <MenuItem onClick={() => deleteList(listInfo.id)}>
                Delete
              </MenuItem>
            </MenuList>
          </Menu>
        </Flex>
        <VStack w={'16em'}>
          {cards.map((card) => (
            <BoardCard cardInfo={card} key={card.id} />
          ))}
        </VStack>
        <Button w={'100%'} mt={'0.5em'} onClick={() => createCard(listInfo.id)}>
          +
        </Button>
      </Box>
    </>
  );
};

export default BoardList;
