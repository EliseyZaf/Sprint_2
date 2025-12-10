package samsungcampus.sprint2.elikur.core.addative;

public class ScreenSize {
    private int width;
    private int height;

    public enum ScreenSizes {
        HD(1280, 720);

        private int width;
        private int height;

        ScreenSizes(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }

    public ScreenSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public ScreenSize(ScreenSizes size) {
        this.width = size.width;
        this.height = size.height;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
