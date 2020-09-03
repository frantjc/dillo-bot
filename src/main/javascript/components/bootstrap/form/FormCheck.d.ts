import {
    DefaultProps,
} from '.';

export interface FormCheckProps extends DefaultProps {
    inline: boolean | 'true' | 'false';
}

export default function FormCheck(props: FormCheckProps): JSX.Element;
