class Positions {
    static RELATIVE: string = 'relative';
    static ABSOLUTE: string = 'absolute';
}

class Colors {
    static GREEN: string = 'green';
    static YELLOW: string = 'yellow';
    static BLACK: string = 'black';
    static WHITE: string = 'white';
}

class Cursors {
    static CANGRAB: string = 'grab';
    static HOLDING: string = 'grabbing';
    static NODROP: string = 'no-drop';
    static POINTER: string = 'pointer';
}

type Direction = 'left' | 'right';

type Column = string | number;

export const classes = {
    container: 'container',
    row: 'row',
    col: (i: Column) =>`col-${i}`,
    nav: (direction: Direction) => `navbar-nav ${direction.toString() === 'right'
        ? 'ml-auto'
        : 'mr-auto'
    }`,
    navItem: 'nav-item',
    navLink: 'nav-link',
    dropdownMenu: 'dropdown-menu',
    dropdownItem: 'dropdown-item',
    navbar: 'navbar navbar-dark bg-dark navbar-expand-lg',
    navBrand: 'navbar-brand',
    navbarToggler: 'navbar-toggler',
    navbarTogglerIcon: 'navbar-toggler-icon',
    navbarCollapse: 'navbar-collapse collapse',
    formGroup: 'form-group',
    formControl: 'form-control',
    formInline: 'form-inline',
    formHelp: 'form-text text-muted',
    formRow: 'form-row',
    formCheck: 'form-check',
    formLabel: 'col-form-label',
    button: 'btn btn-primary',
    drawerLeft: 'drawer-left',
    drawerRight: 'drawer-right',
    drawerButton: 'drawer-button',
    folder: 'folder',
    inputGroup: 'input-group',
    inputGroupPrepend: 'input-group-prepend',
    inputGroupText: 'input-group-text',
    quadrants: 'quadrants',
    quadrant: 'quadrant',
    I: 'I',
    II: 'II',
    III: 'III',
    IV: 'IV',
    tableSpace: 'table-space',
    tableDraggable: 'table-draggable',
    table: 'table',
    tableDark: 'table-dark',
    tableStriped: 'table-striped',
    tableBordered: 'table-bordered',
    tableHover: 'table-hover',
    tableSm: 'table-sm',
    thead: 'thead',
    toolbar: 'toolbar',
    caption: 'caption',
    scrollable: 'scrollable',
    scrollbar: 'scrollbar',
}

export const styles = {
    draggable: (isDragging: boolean) => ({
        cursor: isDragging
            ? Cursors.HOLDING
            : Cursors.CANGRAB,
    }),
    droppable: (isOver: boolean, canDrop: boolean) => ({
        position: Positions.RELATIVE,
        top: 0,
        left: 0,
        height: '100%',
        width: '100%',
        zIndex: 9,
        opacity: 0.4,
        backgroundColor: isOver
            ? ( canDrop
                ? Colors.GREEN
                : Colors.YELLOW
            ) : null,
        cursor: isOver && !canDrop
            ? Cursors.NODROP
            : null,
    })
}