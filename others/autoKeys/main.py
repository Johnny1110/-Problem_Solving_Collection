import time
import threading

from Keyboard import Keyboard
from Mouse import Mouse

from pynput import keyboard

MAX_POTS_X = 1457
MAX_POTS_Y = 777

AMOUNT_POTS_X = 1441
AMOUNT_POTS_Y = 878

BUY_POTS_X = 1353
BUY_POTS_Y = 1033

flag = True

mouse = Mouse()
keys = Keyboard()

def detectMAXButton():
    while True:
        cmd1 = input("detect your cursor position after 3 secs later, please move your cursor to MAX button and wait for count down, type 'y' to continue.\n")
        if cmd1.__eq__("y"):
            break

    while True:
        countdown(3)
        x, y = mouse.get_position()
        global MAX_POTS_X, MAX_POTS_Y
        MAX_POTS_X = x
        MAX_POTS_Y = y
        cmd2 = input("detect MAX button position is [" + str(MAX_POTS_X) + ", " + str(MAX_POTS_Y) + "], type 'y' to continue, type 'n' to rescan.\n")
        if cmd1.__eq__("y"):
            break
        if cmd1.__eq__("n"):
            continue

def detectAmountColumn():
    while True:
        cmd1 = input("detect your cursor position after 3 secs later, please move your cursor to Amount Column and wait for count down, type 'y' to continue.\n")
        if cmd1.__eq__("y"):
            break

    while True:
        countdown(3)
        x, y = mouse.get_position()
        global AMOUNT_POTS_X, AMOUNT_POTS_Y
        AMOUNT_POTS_X = x
        AMOUNT_POTS_Y = y
        cmd2 = input("detect Amount Column position is [" + str(AMOUNT_POTS_X) + ", " + str(AMOUNT_POTS_Y) + "], type 'y' to continue, type 'n' to rescan.\n")
        if cmd1.__eq__("y"):
            break
        if cmd1.__eq__("n"):
            continue

def detectBUYButton():
    while True:
        cmd1 = input("detect your cursor position after 3 secs later, please move your cursor to BUY button and wait for count down, type 'y' to continue.\n")
        if cmd1.__eq__("y"):
            break

    while True:
        countdown(3)
        x, y = mouse.get_position()
        global BUY_POTS_X, BUY_POTS_Y
        BUY_POTS_X = x
        BUY_POTS_Y = y
        cmd2 = input("detect BUY button position is [" + str(BUY_POTS_X) + ", " + str(BUY_POTS_Y) + "], type 'y' to continue, type 'n' to rescan.\n")
        if cmd1.__eq__("y"):
            break
        if cmd1.__eq__("n"):
            continue

def countdown(sec=3):
    for i in range(sec, 0, -1):
        print("> ", i)
        time.sleep(1)


def autoJob():
    while flag:
        mouse.move_mouse((MAX_POTS_X, MAX_POTS_Y))
        time.sleep(0.2)
        keys.left_click_down(MAX_POTS_X, MAX_POTS_Y)
        time.sleep(0.2)
        keys.left_click_up(MAX_POTS_X, MAX_POTS_Y)

        time.sleep(1)

        mouse.move_mouse((AMOUNT_POTS_X, AMOUNT_POTS_Y))
        time.sleep(0.2)
        keys.left_click_down(AMOUNT_POTS_X, AMOUNT_POTS_Y)
        time.sleep(0.2)
        keys.left_click_up(AMOUNT_POTS_X, AMOUNT_POTS_Y)

        time.sleep(1)

        keys.PressKey(keys.ENTER)
        time.sleep(0.5)
        keys.ReleaseKey(keys.ENTER)

        time.sleep(1)

        mouse.move_mouse((BUY_POTS_X, BUY_POTS_Y))
        time.sleep(0.2)
        keys.left_click_down(BUY_POTS_X, BUY_POTS_Y)
        time.sleep(0.2)
        keys.left_click_up(BUY_POTS_X, BUY_POTS_Y)

        time.sleep(1)

        keys.PressKey(keys.ENTER)
        time.sleep(0.5)
        keys.ReleaseKey(keys.ENTER)

        print(".")
        time.sleep(1)


def on_press(key):
    try:
        vk = key.value.vk
        if vk is 121:
            global flag
            flag = not flag

            if flag:
                print("restart job.")
                t = threading.Thread(target=autoJob)
                t.start()
            else:
                print("stop job.")


    except Exception:
        pass

def on_release(key):
    return True

def runMainScript():
    while True:
        cmd1 = input("now everything is ready, press 'y' to execute script after 3 secs count down, press F10 to stop or restart.\n")
        if cmd1.__eq__("y"):
            break

    countdown(3)

    # 建立一個子執行緒
    t = threading.Thread(target=autoJob)
    t.start()

    with keyboard.Listener(on_press=on_press, on_release=on_release) as listener:
        listener.join()







if __name__ == '__main__':
    cmd1 = ""
    while True:
        cmd1 = input("autiKeys info: using default config please type 'y', manual config type 'n'\n")
        if cmd1.__eq__("y") or cmd1.__eq__("n"):
            break

    if cmd1.__eq__("n"):
        detectMAXButton()
        detectAmountColumn()
        detectBUYButton()

    runMainScript()

