import { Select } from '@chakra-ui/react';
import { ChangeEvent } from 'react';
import { BoardInfo } from './lib/api';

const BoardSelector = ({
  boardIndex: boardId,
  boards,
  setBoardIndex,
}: {
  boardIndex?: number;
  boards: BoardInfo[] | undefined;
  setBoardIndex: (index: number) => void;
}) => {
  return (
    <Select
      w={'20ch'}
      value={boardId}
      onChange={(e: ChangeEvent<HTMLSelectElement>) =>
        setBoardIndex(Number(e.target.value))
      }
    >
      {boards?.map(({ id, title }, index) => (
        <option value={index} key={id}>
          {id}: {title}
        </option>
      ))}
    </Select>
  );
};

export default BoardSelector;
