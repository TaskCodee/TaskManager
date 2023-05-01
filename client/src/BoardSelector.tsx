import { Select } from '@chakra-ui/react';
import { ChangeEvent } from 'react';
import { BoardInfo } from './App';

const BoardSelector = ({
  boardId,
  boards,
  selectBoard,
}: {
  boardId?: number;
  boards: BoardInfo[];
  selectBoard: (id: number) => void;
}) => {
  return (
    <Select
      w={'20ch'}
      value={boardId}
      onChange={(e: ChangeEvent<HTMLSelectElement>) =>
        selectBoard(Number(e.target.value))
      }
    >
      {boards.map(({ id, title }) => (
        <option value={id} key={id}>
          {id}: {title}
        </option>
      ))}
    </Select>
  );
};

export default BoardSelector;
