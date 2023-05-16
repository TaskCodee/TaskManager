import {
  Box,
  Card,
  CardBody,
  Editable,
  EditablePreview,
} from '@chakra-ui/react';
import AutoResizeTextarea from './AutoResizeTextarea';
import { CardInfo } from './lib/api';
import { useEffect, useState } from 'react';
import { DragHandleIcon } from '@chakra-ui/icons';
import { DraggableProvidedDragHandleProps } from 'react-beautiful-dnd';

const BoardCard = ({
  cardInfo,
  dragHandleProps,
}: {
  cardInfo: CardInfo;

  dragHandleProps: DraggableProvidedDragHandleProps | null | undefined;
}) => {
  const [title, setTitle] = useState('');

  useEffect(() => {
    setTitle(cardInfo.title);
  }, [cardInfo]);

  const handleChange = (value: string) => {
    setTitle(value);
  };

  return (
    <Card w={'16em'}>
      <CardBody textAlign={'start'}>
        <Box {...dragHandleProps}>
          <DragHandleIcon />
        </Box>
        <Editable value={title} onChange={handleChange}>
          <EditablePreview />
          <AutoResizeTextarea />
        </Editable>
      </CardBody>
    </Card>
  );
};

export default BoardCard;
