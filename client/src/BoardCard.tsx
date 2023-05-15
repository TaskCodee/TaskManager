import { Card, CardBody, Editable, EditablePreview } from '@chakra-ui/react';
import AutoResizeTextarea from './AutoResizeTextarea';
import { CardInfo } from './lib/api';
import { useEffect, useState, useContext } from 'react';
import { BoardContext, BoardContextType } from './BoardContext';

const BoardCard = ({ cardInfo }: { cardInfo: CardInfo }) => {
  const { deleteCard } = useContext(BoardContext) as BoardContextType;
  const [title, setTitle] = useState('');

  useEffect(() => {
    setTitle(cardInfo.title);
  }, [cardInfo]);

  const handleChange = (value: string) => {
    console.log(value);
    setTitle(value);
  };

  const handleBlur = () => {
    if (!title.trim().length) deleteCard(Number(cardInfo.id));
  };

  return (
    <Card w={'16em'} key={cardInfo.id}>
      <CardBody textAlign={'start'}>
        <Editable value={title} onChange={handleChange} onBlur={handleBlur}>
          <EditablePreview />
          <AutoResizeTextarea />
        </Editable>
      </CardBody>
    </Card>
  );
};

export default BoardCard;
