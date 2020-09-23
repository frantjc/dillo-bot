import React, { useState } from 'react';
import { useDrop } from 'react-dnd';
import { FaBoxOpen as BoxOpenIcon } from 'react-icons/fa';

import { DragTypes } from '../../constants';

import { Table } from '../table';

import { styles, classes } from '../../styles';

function TableSpace({ droppable = true, className }) {
  const [{ isOver, canDrop }, drop] = useDrop({
    accept: DragTypes.TABLE,
    drop: (item, monitor) => {
      setTable(item);
    },
    canDrop: (item, monitor) => droppable,
    collect: (monitor) => ({
      isOver: !!monitor.isOver(),
      canDrop: !!monitor.canDrop(),
    }),
  });
  const [table, setTable] = useState({});

  return (
    <div
      ref={drop}
      className={`${classes.tableSpace}${className ? ` ${className}` : ''}`}
    >
      {isOver ? (
        <div style={styles.droppable(isOver, canDrop)} />
      ) : table.name && table.getData ? (
        <Table table={table} draggable={false} />
      ) : (
        <>
          <BoxOpenIcon size={50} color={'#272a30'} />
          <p className={classes.caption}>Drop table here.</p>
        </>
      )}
    </div>
  );
}

export default TableSpace;
