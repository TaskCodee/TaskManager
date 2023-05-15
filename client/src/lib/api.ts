import useSWR from 'swr';
import useSWRMutation from 'swr/mutation';

export type BoardInfo = {
  id: number;
  title: string;
  lists?: ListInfo[];
};

export type ListInfo = {
  id: number;
  title: string;
  cards: CardInfo[];
};

export type CardInfo = {
  id: number;
  title: string;
  description: string;
};

const fetcher = (input: RequestInfo | URL, init?: RequestInit | undefined) =>
  fetch(input, init).then((res) => res.json());

const sendPost = (url: RequestInfo, { arg }: { arg: unknown }) =>
  fetch(url, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(arg),
  });

const sendDelete = (url: RequestInfo, { arg }: { arg: { id: number } }) =>
  fetch(`${url}/${arg.id}`, {
    method: 'DELETE',
  });

export const useBoards = () => {
  const { data, error, isLoading, mutate } = useSWR<BoardInfo[]>(
    '/api/boards',
    fetcher
  );

  return {
    boards: data,
    boardsLoading: isLoading,
    boardsError: error,
    boardsMutate: mutate,
  };
};

export const useBoard = (id: number | null) => {
  const { data, error, isLoading, mutate } = useSWR<BoardInfo>(
    id ? `/api/boards/${id}` : null,
    fetcher
  );

  return {
    board: data,
    boardLoading: isLoading,
    boardError: error,
    boardMutate: mutate,
  };
};

export const useCreateBoard = () => {
  const { trigger } = useSWRMutation('/api/boards', sendPost);
  return { triggerCreateBoard: trigger };
};

export const useCreateList = () => {
  const { trigger } = useSWRMutation('/api/lists', sendPost);
  return { triggerCreateList: trigger };
};

export const useDeleteList = () => {
  const { trigger } = useSWRMutation(`/api/lists`, sendDelete);
  return { triggerDeleteList: trigger };
};

export const useCreateCard = () => {
  const { trigger } = useSWRMutation('/api/cards', sendPost);
  return { triggerCreateCard: trigger };
};
