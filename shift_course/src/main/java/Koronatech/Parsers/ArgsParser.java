package Koronatech.Parsers;


public class ArgsParser {
    private boolean isOkay = true;
    private String outputType = "console";
    private boolean stat = false;
    private String path = null;
    private String order = null;
    private String sortType = null;
    private boolean isOutput = false;

    public boolean parseSort(String arg) {
        if (arg.startsWith("--sort=") || arg.startsWith("-s=")) {
            String type = arg.substring(arg.lastIndexOf("=") + 1);
            if (type.equals("name") || type.equals("salary")) {
                if (this.sortType != null) {
                    System.err.println("Параметр сортировки указан несколько раз");
                    isOkay = false;
                } else {
                    this.sortType = type;
                }
            } else {
                System.err.println("Указан недопустимый параметр сортировки");
            }
            return true;
        }
        return false;
    }

    public boolean parseOrder(String arg) {
        if (arg.startsWith("--order=")) {
            String type = arg.substring(arg.lastIndexOf("=") + 1);
            if (type.equals("asc") || type.equals("desc")) {
                if (this.order != null) {
                    System.err.println("Порядок сортировки указан несколько раз");
                    isOkay = false;
                } else {
                    this.order = type;
                }
            } else {
                System.err.println("Указан недопустимый порядок сортировки");
            }
            return true;
        }
        return false;
    }

    public boolean hasStat(String arg) {
        if (arg.startsWith("--stat")) {
            this.stat = true;
            return true;
        }
        return false;
    }

    public boolean parseOutput(String arg) {
        if (arg.startsWith("--output=") || arg.startsWith("-o=")) {
            String type = arg.substring(arg.lastIndexOf("=") + 1);
            if (type.equals("file")||type.equals("console")) {
                if (this.isOutput) {
                    System.err.println("Формат вывода статистики был указан несколько раз");
                    isOkay = false;
                } else {
                    this.outputType = type;
                    isOutput=true;
                }
            } else  {
                System.err.println("Указан неизвестный тип вывода");
            }
            return true;
        }
        return false;
    }

    public boolean parsePath(String arg) {
        if (arg.startsWith("--path=")) {
            if (this.path != null) {
                System.err.println("Указаны несколько путей файла для статистики");
                isOkay = false;
            } else {
                this.path = arg.substring(arg.lastIndexOf("=") + 1);
            }
            return true;
        }
        return false;
    }

    public void validate() {
        if (order != null && sortType == null) {
            System.err.println("Порядок сортировки нельзя указывать без параметра");
            isOkay = false;
        }
        if (outputType.equals("file") && path == null) {
            System.err.println("Для выведения статистики в файл необходимо указать его путь");
            isOkay = false;
        }
        if (path!=null && outputType.equals("console")) {
            System.err.println("Для выведения статистики в файл необходимо указать соответсвующий тип вывода");
            isOkay = false;
        }
        if (!stat && (isOutput || path != null)) {
            System.err.println("Статистика не может быть получена без указания соответсвующего флага.");
            isOkay = false;
        }
    }

    public boolean parseArgs(String[] args) {
        for (int i=0; i< args.length; i++) {
            args[i] = args[i].trim();
            if (parseSort(args[i])) continue;
            if (parseOrder(args[i])) continue;
            if (hasStat(args[i])) continue;
            if (parseOutput(args[i])) continue;
            if (parsePath(args[i])) continue;
            System.err.println("Неизвестный параметр: " + args[i]);
        }
        validate();
        return isOkay;
    }

    public String getOrder() { return order; }

    public String getOutputType() { return outputType; }

    public String getPath() { return path; }

    public String getSortType() { return sortType; }

    public boolean isStat() { return stat; }
}
