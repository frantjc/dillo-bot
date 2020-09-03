import {
    DefaultProps,
} from '.';

export interface ButtonProps extends DefaultProps {
    type?: 
        | 'button'
        | 'submit'
        | 'reset';
    onClick?: ((event: React.MouseEvent<HTMLButtonElement, MouseEvent>) => void);
}

export default function Button(props: ButtonProps): JSX.Element;
