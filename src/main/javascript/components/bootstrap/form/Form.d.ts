import {
    DefaultProps,
} from '.';

export interface FormProps extends DefaultProps {
    inline: boolean | 'true' | 'false';
}

export default function Form(props: FormProps): JSX.Element;