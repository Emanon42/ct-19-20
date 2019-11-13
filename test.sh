# $1: compiler mode (lexer|parser...)



sourcefilesGen=`ls ./tests/gen/source`
num_source=`ls -1 ./tests/gen/source | wc -l`
gen_counter=0


error_counter=0
pass_counter=0
lightyel='\E[1;33m'
lightred='\E[1;91m'
wipe="\033[1m\033[0m"
ant build > /dev/null 2>&1

if [ $1 = "gen" ]; then
    for eachfile in $sourcefilesGen
    do
        echo compiling $eachfile
        java -cp bin Main -gen tests/gen/source/$eachfile dummy.asm
        echo executing asm
        java -jar Mars4_5.jar sm nc me dummy.asm > outputTmp.txt
        sed -i -e '$a\' outputTmp.txt
        expected="./tests/gen/expected/$eachfile"
        output="./outputTmp.txt"
        cmp -s "$output" "$expected" > tmp.txt
        result=$?
        if [ $result -eq 0 ]
        then
            echo output correct
            gen_counter=`bc <<< $gen_counter+1`
        else
            echo "ERROR $eachfile"
        fi
        #rm outputTmp.txt
        rm tmp.txt
    done
    
    echo gen test: $gen_counter / $num_source




else

testfiles_error=`ls ./tests/$1/error`
testfiles_pass=`ls ./tests/$1/pass`
num_t_f_e=`ls -1 ./tests/$1/error | wc -l`
num_t_f_p=`ls -1 ./tests/$1/pass | wc -l`


for eachtest in $testfiles_error
do
    
    java -cp bin Main -$1 tests/$1/error/$eachtest dummy.out > tmp.txt
    result=$?
    #cmd=$(cat tmp.txt)
    #echo "$cmd"
    if [ $result -ne 0 ]
    then
        echo executing tests/$1/error/$eachtest
        error_counter=`bc <<< $error_counter+1`
    else
        echo -e "${lightyel}executing tests/$1/error/$eachtest"
        cmd=$(cat tmp.txt)
        echo "$cmd"
        echo -e "ERROR: it should fail but pass${wipe}"
    fi
done
echo tests should fail: $error_counter / $num_t_f_e
echo
for eachtest in $testfiles_pass
do
    
    java -cp bin Main -$1 tests/$1/pass/$eachtest dummy.out > tmp2.txt
    result=$?
    #echo "$cmd"
    if [ $result -eq 0 ]
    then
        echo executing tests/$1/pass/$eachtest
        pass_counter=`bc <<< $pass_counter+1`
    else
        cmd=$(cat tmp2.txt)
        echo -e "${lightred}executing tests/$1/pass/$eachtest"
        echo "$cmd"
        echo -e "ERROR: it should pass but fail${wipe}"
    fi
done

rm tmp.txt
rm tmp2.txt
echo tests should pass: $pass_counter / $num_t_f_p
fi
ant clean > /dev/null 2>&1
