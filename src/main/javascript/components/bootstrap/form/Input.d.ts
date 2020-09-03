import {
    DefaultProps,
} from '.';

export interface InputProps extends DefaultProps {
    label?: React.ReactNode;
    type?: string;
    placeholder?: string;
    name?: string;
    onChange?: React.ChangeEventHandler<T>;
    onKeyPress?: React.KeyboardEventHandler<T>;
    id?: string;
    help?: string;
    prepend?: React.ReactNode;
    disabled?: boolean | 'true' | 'false';
    valid?: boolean | 'true' | 'false'; // this may be inteneded to be a function that checks for validity
}

export default function Input(props: InputProps): JSX.Element;

