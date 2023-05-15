export type BoardInfo = {
  id: number;
  title: string;
  lists: ListInfo[];
};

export type ListInfo = { id: number; title: string; cards: CardInfo[] };

export type CardInfo = {
  id: number;
  title: string;
  description: string;
};

export const fetcher = async (input: RequestInfo | URL, init?: RequestInit) =>
  await (await fetch(input, init)).json();

export const addList = async (
  curBoard: BoardInfo | undefined,
  { title }: { title: string }
) => {
  const res = await fetch(`/api/boards/${curBoard?.id}/lists`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ title }),
  });
  if (!curBoard) return;

  return { ...curBoard, lists: [...curBoard.lists, (await res.json()).data] };
};
