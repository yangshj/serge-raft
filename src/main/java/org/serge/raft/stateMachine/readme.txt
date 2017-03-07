Once we have a leader elected we need to replicate all changes to our system to all nodes.
This is done by using the same Append Entries message that was used for heartbeats.
Let's walk through the process.
// 这是说心跳也通过entry来发送

First a client sends a change to the leader.
The change is appended to the leader's log...
...then the change is sent to the followers on the next heartbeat.
An entry is committed once a majority of followers acknowledge it...
...and a response is sent to the client.
Now let's send a command to increment the value by "2".
Our system value is now updated to "7".
Raft can even stay consistent in the face of network partitions.

Let's add a partition to separate A & B from C, D & E.
Because of our partition we now have two leaders in different terms.
Let's add another client and try to update both leaders.
One client will try to set the value of node B to "3".
Node B cannot replicate to a majority so its log entry stays uncommitted.
The other client will try to set the value of node C to "8".
This will succeed because it can replicate to a majority.
Now let's heal the network partition.
Node B will see the higher election term and step down.
Both nodes A & B will roll back their uncommitted entries and match the new leader's log.
Our log is now consistent across our cluster.

