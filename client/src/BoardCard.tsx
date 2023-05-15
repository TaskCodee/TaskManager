import { Card, CardBody, Editable, EditablePreview } from '@chakra-ui/react';
import AutoResizeTextarea from './AutoResizeTextarea';
import { CardInfo } from './lib/api';

const BoardCard = ({ cardInfo }: { cardInfo: CardInfo }) => {
  return (
    <Card w={'16em'} key={cardInfo.id}>
      <CardBody textAlign={'start'}>
        <Editable defaultValue={cardInfo.title}>
          <EditablePreview />
          <AutoResizeTextarea />
        </Editable>
      </CardBody>
    </Card>
  );
};

export default BoardCard;
