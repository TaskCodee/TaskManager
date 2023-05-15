import { Card, CardBody, Editable, EditablePreview } from '@chakra-ui/react';
import AutoResizeTextarea from './AutoResizeTextarea';
import { CardInfo } from './lib/api';
import { useEffect, useState } from 'react';

const BoardCard = ({ cardInfo }: { cardInfo: CardInfo }) => {
  const [title, setTitle] = useState('');

  useEffect(() => {
    setTitle(cardInfo.title);
  }, [cardInfo]);

  const handleChange = (value: string) => {
    setTitle(value);
  };

  return (
    <Card w={'16em'} key={cardInfo.id}>
      <CardBody textAlign={'start'}>
        <Editable value={title} onChange={handleChange}>
          <EditablePreview />
          <AutoResizeTextarea />
        </Editable>
      </CardBody>
    </Card>
  );
};

export default BoardCard;
