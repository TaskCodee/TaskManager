import { EditableTextarea, TextareaProps, forwardRef } from '@chakra-ui/react';
import ResizeTextarea from 'react-textarea-autosize';

const AutoResizeTextarea = forwardRef<TextareaProps, 'textarea'>(
  (props, ref) => (
    <EditableTextarea
      minH="unset"
      overflow="hidden"
      w="100%"
      resize="none"
      ref={ref}
      minRows={1}
      as={ResizeTextarea}
      {...props}
    />
  )
);

export default AutoResizeTextarea;
