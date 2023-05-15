export type BoardInfo = { id: number; title: string };
export type BoardData = BoardInfo & { lists: ListData[] };
export type ListData = { id: number; title: string; cards: CardInfo[] };
export type CardInfo = { id: number; title: string; description: string };
