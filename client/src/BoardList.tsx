import { useContext } from 'react';
import { BoardContextType } from './BoardContext';
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
import BoardCard from './BoardCard';
import { AddIcon, ChevronDownIcon } from '@chakra-ui/icons';
import { CardInfo, ListInfo } from './lib/api';
import { BoardContext } from './BoardContext';

const BoardList = ({ listInfo }: { listInfo: ListInfo }) => {
  const { createCard, deleteList, editCard } = useContext(
    BoardContext
  ) as BoardContextType;

  const handleEditCard = (cardInfo: CardInfo) => {
    editCard(listInfo.id, cardInfo);
  };

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
            <MenuButton variant={'ghost'} as={Button}>
              <ChevronDownIcon />
            </MenuButton>
            <MenuList>
              <MenuItem onClick={() => deleteList(listInfo.id)}>
                Delete
              </MenuItem>
            </MenuList>
          </Menu>
        </Flex>
        <VStack w={'16em'}>
          {listInfo.cards.map((card) => (
            <BoardCard
              cardInfo={card}
              onEditCard={handleEditCard}
              key={card.id}
            />
          ))}
        </VStack>
        <Button
          variant={'outline'}
          w={'100%'}
          mt={'0.5em'}
          onClick={() => createCard(listInfo.id)}
        >
          <AddIcon />
        </Button>
      </Box>
    </>
  );
};

export default BoardList;
