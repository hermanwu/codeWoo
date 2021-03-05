本章我们来讲另一类大数据问题：概率类的大数据问题。概率类的大数据问题，本质上是概率问题而不是大数据问题。这类问题中最常出现的则是如何在数据流中等概率的取出 M 个元素。这个问题有标准解法的，知道就知道，不知道一般也很难想到。记住就好。

问题描述：给你一个 Google 搜索日志记录，存有上亿挑搜索记录（Query）。这些搜索记录包含不同的语言。随机挑选出其中的 100 万条中文搜索记录。假设判断一条 Query 是不是中文的工具已经写好了。

这个题是一个经典的概率算法问题。这个问题的本质是一个数据流问题，虽然题目跟你说的是给了你一个“死”文件，但如果你的算法是基于 Offline 的数据的话，面试官也一定会追问一个 Online 的算法，即如何在一条一条的搜索记录飞驰而过的过程中，随机挑选出 100 万条中文搜索记录。考虑 Online 的另一个好处是，如果你的思路朝着离线算法的方向想的话，比如统计一下一共有 M 条记录，那么要从中挑选 N 条，就是用 N/M 的概率去挑选。那这个时候是很难处理非中文的 Queries 的。如果你的算法是一个在线算法的话，处理非中文的 Queries 就很简单，直接跳过就行了。

假设你一共要挑选 N 个 Queries，设置一个 N 的 Buffer，用于存放你选中的 Queries。对于每一条飞驰而过的 Query，按照如下步骤执行你的算法：

如果非中文，直接跳过
如果 Buffer 不满，将这条 Query 直接加入 Buffer 中
如果 Buffer 满了，假设当前一共出了过 M 条中文 Queries，用一个随机函数，以 N / M 的概率来决定这条 Query 是否能被选中留下。
3.1 如果没有选中，则跳过该 Query，继续处理下一条 Query
3.2 如果选中了，则用一个随机函数，以 1 / N 的概率从 Buffer 中随机挑选一个 Query 来丢掉，让当前的 Query 放进去。

虽然已经告诉了你只要记忆就可以了，但是你可能还是疑惑，为什么这样做就可以实现等概率地抽取，那接下来我们就证明一下好了。

为了简化证明过程，我们用 5 条 Queries 里挑 3 条来作为例子证明每条 Query 被挑中的概率都是 3/5。

依次处理每条 Query，前 3 条 Queries 直接进入 Buffer => [1,2,3]，此时前 3 条 Queries 被选中的概率 100%
第 4 条 Query 处理时，有 3/4 的概率被留下，那么第 4 条 Query 被选中的概率此时就是 3/4。
第 4 条 Query 处理时，如果留下之后，会从 Buffer 中以 1/3 的概率踢走一条 Query。那么这些在 Buffer 中留下包含两种情况：一种是第 4 条 Query 没有被选中，概率为 1/4；第二种是第 4 条 Query 被选中的条件下，没有被踢走，概率是 3/4 _ 2/3。所以总概率是 1/4 + 2/3 _ 3/4 = 3/4。其中 1/4 是第 4 条 Query 没有被选中的概率。
综上在处理到第 4 条 Query 的时候，所有 Query 被选中的概率均为 3/4。
第 5 条 Query 处理时，有 3/5 的概率被留下，那么第 5 条 Query 被选中的概率此时就是 3/5。
第 5 条 Query 处理时，如果留下之后，会从 Buffer 中以 1/3 的概率踢走一条 Query。前 4 条 Query 能够顺利进入 Buffer 并被留下，首先要满足大前提，第四条 Query 处理后被留下来，概率为 3/4。在这个大前提下，同 3.分两种情况，第一种是第 5 条 Query 没有被选中，概率是 2/5；第二种是第 5 条 Query 被选中的前提下，没有被踢走，概率是 3/5 _ 2/3。总概率为：：3/4 _ (2/5 + 2/3 \* 3/5) = 3/5。

有了前面一道题的经验，下一道题，我想你一定可以迎刃而解。

问题描述
Amazon: 一个文件中有很多行，不能全部放到内存中，如何等概率的随机挑出其中的一行？[题目来源]（https://www.careercup.com/question?id=13218749）

问题解答
先将第一行设为候选的被选中的那一行，然后一行一行的扫描文件。假如现在是第 K 行，那么第 K 行被选中踢掉现在的候选行成为新的候选行的概率为 1/K。用一个随机函数看一下是否命中这个概率即可。命中了，就替换掉现在的候选行然后继续，没有命中就继续看下一行。