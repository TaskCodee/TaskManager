export type BoardInfo = { id: number; title: string };
export type BoardData = BoardInfo & { lists: ListData[] };
export type ListInfo = { id: number; title: string };
export type ListData = ListInfo & { cards: CardInfo[] };
export type CardInfo = { id: number; title: string; description: string };

export const getBoards = async () => {
  const res = await fetch('/api/boards');
  if (!res.ok) throw new Error(res.statusText);

  const data: BoardInfo[] = await res.json();
  return data;
};

export const getBoard = async (id: number | undefined) => {
  if (id == null) throw new Error('board id is null');
  const res = await fetch(`/api/boards/${id}`);
  if (!res.ok) throw new Error(res.statusText);

  const data: BoardData = await res.json();
  return data;
};

export const createBoard = async (
  userId: number | undefined,
  boardInfo: BoardInfo
) => {
  if (userId == null) throw new Error('user id is null');
  const res = await fetch(`/api/users/${userId}/boards`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(boardInfo),
  });
  if (!res.ok) throw new Error(res.statusText);

  const data: BoardInfo = (await res.json()).data;
  return data;
};

export const createList = async (
  boardId: number | undefined,
  listInfo: ListInfo
) => {
  if (boardId == null) throw new Error('board id is null');
  const res = await fetch(`/api/boards/${boardId}/lists`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(listInfo),
  });
  if (!res.ok) throw new Error(res.statusText);

  const data: ListInfo = (await res.json()).data;
  return data;
};

export const deleteList = async (listId: number | undefined) => {
  if (listId == null) throw new Error('list id is null');
  const res = await fetch(`/api/lists/${listId}`, {
    method: 'DELETE',
  });
  if (!res.ok) throw new Error(res.statusText);
};

export const createCard = async (
  listId: number | undefined,
  cardInfo: CardInfo
) => {
  if (listId == null) throw new Error('list id is null');
  const res = await fetch(`/api/lists/${listId}/cards`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(cardInfo),
  });
  if (!res.ok) throw new Error(res.statusText);

  const data: CardInfo = (await res.json()).data;
  return data;
};
