package com.asyf.demo.hadoop.test001;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;
import java.util.StringTokenizer;

public class WordCount {

    public static class TokenizerMapper
            extends Mapper<Object, Text, Text, IntWritable> {

        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        //前面两个Object key, Text value就是输入的key和value，第三个参数Context context这是可以记录输入的key和value，
        //例如：context.write(word, one);此外context还会记录map运算的状态。
        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens()) {
                word.set(itr.nextToken());
                context.write(word, one);
            }
        }
    }

    public static class IntSumReducer
            extends Reducer<Text, IntWritable, Text, IntWritable> {
        private IntWritable result = new IntWritable();

        //reduce函数的输入也是一个key/value的形式，不过它的value是一个迭代器的形式Iterable<IntWritable> values，
        //也就是说reduce的输入是一个key对应一组的值的value，reduce也有context和map的context作用一致。
        public void reduce(Text key, Iterable<IntWritable> values,
                           Context context
        ) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            result.set(sum);
            context.write(key, result);
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length != 2) {
            System.err.println("Usage: wordcount <in> <out>");
            System.exit(2);
        }
        //构建一个job
        Job job = new Job(conf, "word count");
        //装载程序员编写好的计算程序
        job.setJarByClass(WordCount.class);
        //装载map函数
        job.setMapperClass(TokenizerMapper.class);
        //装载Combiner类---本例去掉第四行也没有关系，但是使用了第四行理论上运行效率会更好。
        job.setCombinerClass(IntSumReducer.class);
        //装载reduce函数
        job.setReducerClass(IntSumReducer.class);

        //这个是定义输出的key/value的类型，也就是最终存储在hdfs上结果文件的key/value的类型。
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //第一行就是构建输入的数据文件，第二行是构建输出的数据文件，最后一行如果job运行成功了，
        // 我们的程序就会正常退出。
        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
