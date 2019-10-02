# $1: compiler mode (lexer|parser...)

testfiles=`ls ./tests/$1`
ant build > /dev/null 2>&1
for eachtest in $testfiles
do
    echo executing tests/$1/$eachtest
    cmd=`java -cp bin Main -$1 tests/$1/$eachtest dummy.out`
    echo $cmd
done
ant clean > /dev/null 2>&1