import { createContext } from 'react';
import { BoardInfo } from './lib/api';

export type BoardContextType = {
  board?: BoardInfo;
  createBoard: (title?: string) => void;
  createList: (boardId: number, title?: string) => void;
  deleteList: (id: number) => void;
  createCard: (listId: number, title?: string, description?: string) => void;
  deleteCard: (id: number) => void;
};

export const BoardContext = createContext<BoardContextType | null>(null);
