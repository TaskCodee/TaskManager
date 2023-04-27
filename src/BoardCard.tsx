import { Card, CardBody, Editable, EditablePreview } from '@chakra-ui/react';
import { CardInfo } from './App';
import AutoResizeTextarea from './AutoResizeTextarea';

const BoardCard = ({ cardInfo }: { cardInfo: CardInfo }) => {
  return (
    <Card w={'16em'} key={cardInfo.id}>
      {/* <CardHeader>{card.title}</CardHeader> */}
      <CardBody textAlign={'start'}>
        <Editable defaultValue={cardInfo.description}>
          <EditablePreview />
          <AutoResizeTextarea />
        </Editable>
      </CardBody>
    </Card>
  );
};

export default BoardCard;
