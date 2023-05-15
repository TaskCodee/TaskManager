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
import { CardInfo, ListData, createCard } from './lib/api';
import { useMutation, useQueryClient } from '@tanstack/react-query';

const BoardList = ({
  list,
  deleteList,
}: {
  list: ListData;
  deleteList: (listId: number) => void;
}) => {
  const queryClient = useQueryClient();
  const cardCreateMutation = useMutation({
    mutationFn: (newCard: CardInfo) => createCard(list.id, newCard),
    onSuccess: () => queryClient.invalidateQueries(['board']),
  });

  return (
    <>
      <Box shadow={'md'} borderRadius={'15'}>
        <Flex
          fontWeight={'semibold'}
          textAlign={'start'}
          mb={'0.5em'}
          align={'center'}
        >
          <Box>{list.title}</Box>
          <Spacer />
          <Menu>
            <MenuButton variant={'ghost'} as={Button}>
              <ChevronDownIcon />
            </MenuButton>
            <MenuList>
              <MenuItem
                onClick={() => {
                  console.log('Delete list');
                  deleteList(list.id);
                }}
              >
                Delete
              </MenuItem>
            </MenuList>
          </Menu>
        </Flex>
        <VStack w={'16em'}>
          {list.cards.map((card) => (
            <BoardCard cardInfo={card} key={card.id} />
          ))}
        </VStack>
        <Button
          variant={'outline'}
          w={'100%'}
          mt={'0.5em'}
          onClick={() => {
            console.log('Create card');
            cardCreateMutation.mutate({
              id: 0,
              title: 'Test card',
              description: '',
            });
          }}
        >
          <AddIcon />
        </Button>
      </Box>
    </>
  );
};

export default BoardList;
