package vertex;

public class Movie extends Vertex {
    private int year;

    private String coutry;
    private double imdb;

    public Movie(String label) {
        super(label);
        year = 0;
        coutry = "";
        imdb = 0.00;
    }

    @Override
    public void fillVertexInfo(String[] args) {
        if (args.length == 3) {
            if (Integer.parseInt(args[0]) < 1900 || Integer.parseInt(args[0]) > 2018) {
                System.out.println("1900~2018");
                return;
            } else {
                year = Integer.parseInt(args[0]);
            }
            coutry = args[1];
            double d = Double.parseDouble(args[2]);
            if (d < 0 || d > 10) {
                System.out.println("0~10");
                return;
            } else {
                String result = String.format("%.2f", d);
                d = Double.parseDouble(result);
                imdb = d;

            }

        }

    }

    public int getYear() {
        return year;
    }

    public String getCoutry() {
        return coutry;
    }

    public double getImdb() {
        return imdb;
    }

    private void setYear(int year) {
        this.year = year;
    }

    private void setCoutry(String coutry) {
        this.coutry = coutry;
    }

    private void setImdb(double imdb) {
        this.imdb = imdb;
    }

    @Override
    public Vertex newVertex() {
        Movie m=new Movie(getLabel());
        m.setYear(getYear());
        m.setImdb(getImdb());
        m.setCoutry(getCoutry());
        return m;
    }

}
