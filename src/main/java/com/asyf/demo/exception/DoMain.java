package com.asyf.demo.exception;

public class DoMain {
    public static void main(String[] args) throws Exception {
        try {
            try {
                throw new Sneeze();
            } catch (Annoyance a) {
                System.out.println("Caught Annoyance");
                throw a;
            }
        } catch (Sneeze s) {
            System.out.println("Caught Sneeze");
            // return;
        } finally {
            System.out.println("end");
        }
        System.out.println("========================\r\n" +
                "上面代码打印Caught Sneeze是因为throw a 是引用的Sneeze()对象" +
                "下面代码打印Annoyance因为抛出的异常时Annoyance对象" +
                "若throw new Sneeze();则打印Sneeze" +
                "\r\n========================");
        try {
            throw new Sneeze();
            //throw new Annoyance();
        } catch (Sneeze s) {
            System.out.println("Sneeze");
        } catch (Annoyance a) {
            System.out.println("Annoyance");
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }

}
