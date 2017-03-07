In Raft there are two timeout settings which control elections.
First is the election timeout.
The election timeout is the amount of time a follower waits until becoming a candidate.
The election timeout is randomized to be between 150ms and 300ms.
After the election timeout the follower becomes a candidate and starts a new election term...
...votes for itself...
...and sends out Request Vote messages to other nodes.
If the receiving node hasn't voted yet in this term then it votes for the candidate...
...and the node resets its election timeout.
Once a candidate has a majority of votes it becomes leader.



The leader begins sending out Append Entries messages to its followers.
These messages are sent in intervals specified by the heartbeat timeout.
Followers then respond to each Append Entries message.
This election term will continue until a follower stops receiving heartbeats and becomes a candidate.


Let's stop the leader and watch a re-election happen.
Node C is now leader of term 2.
Requiring a majority of votes guarantees that only one leader can be elected per term.
If two nodes become candidates at the same time then a split vote can occur.
Let's take a look at a split vote example...
Two nodes both start an election for the same term...
..and each reaches a single follower node before the other.
Now each candidate has 2 votes and can receive no more for this term.
The nodes will wait for a new election and try again.
Node A received a majority of votes in term 6 so it becomes leader.


