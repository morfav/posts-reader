import React from 'react';
import {makeStyles} from '@material-ui/core/styles';
import CardContent from '@material-ui/core/CardContent';
import Typography from '@material-ui/core/Typography';

const useStyles = makeStyles({
    root: {
        backgroundColor: "lightsteelblue"
    },
    title: {
        fontSize: 14,
    },
    pos: {
        marginBottom: 12,
    },
});

const Comment = ({comment: {name, email, body}}) => {
    const classes = useStyles();

    return (
        <CardContent className={classes.root}>
            <Typography className={classes.title} color="textPrimary" gutterBottom>
                {name} ({email})
            </Typography>
            <Typography className={classes.pos} color="textSecondary">
                {body}
            </Typography>
        </CardContent>
    );
}

export default Comment;