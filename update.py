from random import randint
from queue import PriorityQueue

# Get amd PUt are tje funcs

def update_linaer_no_dep(size):
    q = PriorityQueue()
    track = {}
    update_num = size/2
    to_update = set()
    random_ins = randint(1, size)
    resolved = size + random_ins
    range_start = 1
    range_end = 100
    while len(to_update) < update_num:
        to_update.add(randint(1,(random_ins + size-1)))
    # print(to_update)
    expected_cmds = update_num + random_ins + size + resolved +1
    x = ""
    x += f"{int(expected_cmds)}\n"
    # Insert first batch
    for i in range(0, size):
        urg = randint(range_start,range_end)
        x += f"1 T{i+1} {urg}\n"
        track[(i+1)] = 0-urg
    # print(track)
    # A random amount of inserts
    for i in range(random_ins):
        urg = randint(range_start,range_end)
        x+= f"1 T{i+size+1} {urg}\n"
        track[(i+1+size)] =0- urg

    # Update Randomly Selected Nodes
    for i in to_update:
        urg = randint(range_start,range_end)
        x += f"2 T{i} {urg}\n"
        track[i] = 0-urg

    for k,v in track.items():
        q.put((v,f"T{k}"))
    # resolved
    for i in range(resolved):
        x += "3\n"

    line = ""

    while q.empty() is False:
        z = q.get()

        line += f"{z[1]} "
        print(z)
    x += f"\n{resolved}\n"
    x += f"{line[0:-1]}\n"
    return(x)
        


f = open("tests/testfile2.txt","w")
f.write(update_linaer_no_dep(40))
f.close()