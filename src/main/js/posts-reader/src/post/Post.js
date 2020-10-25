import React, {useState} from 'react';
import {makeStyles} from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import Comment from "./Comment";

const useStyles = makeStyles({
    root: {
        minWidth: 275,
    },
    title: {
        fontSize: 14,
    },
    pos: {
        marginBottom: 12,
    },
});

const Post = ({post: {title, body, comments}}) => {
    const classes = useStyles();
    const [isCommentsVisible, setCommentVisible] = useState(false);

    return (
        <Card className={classes.root} variant="outlined">
            <CardContent>
                <Typography className={classes.title} color="textPrimary" gutterBottom>
                    {title}
                </Typography>
                <Typography className={classes.pos} color="textSecondary">
                    {body}
                </Typography>
            </CardContent>
            <CardActions>
                <Button size="small" onClick={() => setCommentVisible(!isCommentsVisible)}>Comments</Button>
            </CardActions>
            {isCommentsVisible && comments.map(comment => <Comment key={comment.id} comment={comment}/>)}
        </Card>
    );
}

export default Post;